const User = require('../models/User');
const {uploader, sendEmail} = require('../utils/index');
const {validationResult} = require('express-validator')
const ObjectId = require('mongoose').Types.ObjectId


exports.createUser = async function (req, res) {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }
    let{userName, password} = req.body;
    let user = await User.exists({userName: userName})

    if (user){
        return res.status(402).json({
            success: false,
            message: 'user already exists'
        })
    }
    const userData = {
        userName: userName,
        password: password
    }
    User(userData).save((err)=>{
        if(err){
            return res.status(401).json({
                success: false,
                message: err
            })
        }
        return res.status(200).json({success: true});
    })
}

exports.delAllUsers = async (req, res) => {
    User.deleteMany({}, (err, data) => {
        if (err) {
            return res.status(400).json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
}

exports.findAllUsers = async function (req, res) {
    User.find({},(err, data)=>{
        if(err){
            return res.status(400).json({
                success: false,
                error: err
            })
        }
        return res.status(200).json({
            success: true,
            data: data
        })
    })
}

exports.checkPasswordMatch = async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }
    User.findOne({userName: req.body.userName }, async (err, data) =>{
        if(!data){  return res.status(405).json({
            success: false,
            error: 'user not found'
        })}
        let passwordMatching = await data.comparePassword(req.body.password);
        console.log(data.password)
        console.log(req.body.password)
        console.log(passwordMatching)
        return res.status(200).json({
            success: true,
            data: passwordMatching
        })
    })
}




//not my part



// @route GET admin/user
// @desc Returns all users
// @access Public
exports.index = async function (req, res) {
    const users = await User.find({});
    res.status(200).json({users});
};

// @route POST api/user
// @desc Add a new user
// @access Public
exports.store = async (req, res) => {
    try {
        const {email} = req.body;

        // Make sure this account doesn't already exist
        const user = await User.findOne({email});

        if (user) return res.status(401).json({message: 'The email address you have entered is already associated with another account. You can change this users role instead.'});

        const password = '_' + Math.random().toString(36).substr(2, 9); //generate a random password
        const newUser = new User({...req.body, password});

        const user_ = await newUser.save();

        //Generate and set password reset token
        user_.generatePasswordReset();

        // Save the updated user object
        await user_.save();

        //Get mail options
        let domain = "http://" + req.headers.host;
        let subject = "New Account Created";
        let to = user.email;
        let from = process.env.FROM_EMAIL;
        let link = "http://" + req.headers.host + "/api/auth/reset/" + user.resetPasswordToken;
        let html = `<p>Hi ${user.username}<p><br><p>A new account has been created for you on ${domain}. Please click on the following <a href="${link}">link</a> to set your password and login.</p> 
                  <br><p>If you did not request this, please ignore this email.</p>`

        await sendEmail({to, from, subject, html});

        res.status(200).json({message: 'An email has been sent to ' + user.email + '.'});

    } catch (error) {
        res.status(500).json({success: false, message: error.message})
    }
};

// @route GET api/user/{id}
// @desc Returns a specific user
// @access Public
exports.show = async function (req, res) {
    try {
        const id = req.params.id;

        const user = await User.findById(id);

        if (!user) return res.status(401).json({message: 'User does not exist'});

        res.status(200).json({user});
    } catch (error) {
        res.status(500).json({message: error.message})
    }
};

// @route PUT api/user/{id}
// @desc Update user details
// @access Public
exports.update = async function (req, res) {
    try {
        const update = req.body;
        const id = req.params.id;
        const userId = req.user._id;

        //Make sure the passed id is that of the logged in user
        if (userId.toString() !== id.toString()) return res.status(401).json({message: "Sorry, you don't have the permission to upd this data."});

        const user = await User.findByIdAndUpdate(id, {$set: update}, {new: true});

        //if there is no image, return success message
        if (!req.file) return res.status(200).json({user, message: 'User has been updated'});

        //Attempt to upload to cloudinary
        const result = await uploader(req);
        const user_ = await User.findByIdAndUpdate(id, {$set: update}, {$set: {profileImage: result.url}}, {new: true});

        if (!req.file) return res.status(200).json({user: user_, message: 'User has been updated'});

    } catch (error) {
        res.status(500).json({message: error.message});
    }
};

// @route DESTROY api/user/{id}
// @desc Delete User
// @access Public
exports.destroy = async function (req, res) {
    try {
        const id = req.params.id;
        const user_id = req.user._id;

        //Make sure the passed id is that of the logged in user
        if (user_id.toString() !== id.toString()) return res.status(401).json({message: "Sorry, you don't have the permission to delete this data."});

        await User.findByIdAndDelete(id);
        res.status(200).json({message: 'User has been deleted'});
    } catch (error) {
        res.status(500).json({message: error.message});
    }
};

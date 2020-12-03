const mongoose = require('mongoose');
const express = require('express');
let cors = require('cors');
const bodyParser = require('body-parser');
const logger = require('morgan');


const Plan = require ('./models/Plan');
const Task = require ('./models/Task');
const User = require ('./models/User');



const ObjectId = mongoose.Types.ObjectId;

const http = require('http')
const socketIo = require('socket.io')

const events = require('events');


const password = require('../pw/pw.json');


const API_PORT = process.env.PORT || 3001;
const app = express();
const server = http.createServer(app);
const io = socketIo(server);


const eventEmitter = new events.EventEmitter();

app.use(cors());


const router = express.Router();


// this is our MongoDB database
    const dbRoute =
        `mongodb+srv://Neekh:${password.pw}@cluster0.surv1.mongodb.net/Cluster0?retryWrites=true&w=majority`;

// connects our back end code with the database
    mongoose.connect(dbRoute, {useUnifiedTopology: true, useNewUrlParser: true})
        .then( res => {})
        .catch(error => {console.log(error)
                        process.exit()
        })

    let db = mongoose.connection;

    db.once('open', () => console.log('connected to the database'));

// checks if connection with the database is successful
    db.on('error', console.error.bind(console, 'MongoDB connection error:'));

// (optional) only made for logging and
// bodyParser, parses the request body to be a readable json format
    app.use(bodyParser.urlencoded({extended: false}));
    app.use(bodyParser.json());
    app.use(logger('dev'));


io.on('connection', (socket) => {
    console.log('new connection!');
    eventEmitter.on('update', (message) => socket.emit("dbUpdated", message))
})

// this is our get method
// this method fetches all available data in our database

router.get('/findPlanToOwner', (req, res) => {

    let owner = req.body.owner;

    if (!owner)
    return res.json({
        success: false,
        error: 'INVALID INPUTS',
    });

    Plan.find( {owner: owner} ,(err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
});

router.get('/findAllPlans', (req, res) => {
    //let {planId} = req.body;
    let planId = "5fa2ea499c0cacc6ca5b9f02";
    let objId = new ObjectId(planId);
    Plan.find((err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
});

router.get('/findAllTasks', (req, res) => {
    Task.find((err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
});
//delete all tasks of a given planId
router.delete('/delTasks', (req, res) => {

    let planId = req.body.planId;

    if (!planId) {
        return res.json({
            success: false,
            error: 'INVALID INPUTS',
        });
    }

    Task.deleteMany({ plan: planId}, (err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            Plan.updateOne(
                { _id: planId },
                {
                    tasks: []
                },
                (err, updatedPlan) => { if(err) {console.log(err)} else {console.log(updatedPlan)}} )
            return res.json({success: true, data: data});
        }
    });
});

router.delete('/delSingleTask', (req, res) => {

    let taskId = req.body.taskId;

    if (!taskId) {
        return res.json({
            success: false,
            error: 'INVALID INPUTS',
        });
    }

    var planId = '';

    Task.findOne({_id: taskId}, (err, data) => {
        if(err)
            return res.json({
                success: false,
                error: 'INVALID INPUTS',
            });
        if (!data)
            return res.json({
            success: false,
            error: 'Task not found',
        });
        else {
            planId = data.plan;
            console.log(planId);
            Task.deleteOne({_id: taskId}, (err, data) => {
                if (err) {
                    return res.json({success: false, error: err});
                } else {
                    Plan.updateOne(
                        {_id: planId},
                        {
                            $pull: {
                                // omg man muss hier auf ObjectId casten sonst vergleicht er falsch..
                                tasks: {taskId: ObjectId(taskId)}
                            }
                        },
                        (err, updatedPlan) => {
                            if (err) {
                                console.log(err)
                            } else {

                            }
                        })
                    return res.json({success: true, data: data});
                }
            })
        }
    })
});


//create a plan, receives the user and planName => when authorization is done use emailAddress as user
router.post('/createPlan', (req, res) => {
    let plan = new Plan();
    const {name, messageSender } = req.body;
    if (!name || !messageSender) {
        return res.json({
            success: false,
            error: 'INVALID INPUTS',
        });
    }
    plan.name = name;
    plan.users = [messageSender];
    plan.owner = messageSender;
    plan.tasks = [];
    plan.save((err) => {
        if (err) return res.json({success: false, error: err});
        return res.json({success: true});
    });
});

router.delete('/deletePlan', (req, res) => {
    const {id} = req.body;
    console.log(req.body);
    if (!id) {
        return res.json({
            success: false,
            error: 'NO ID',
        });
    }
    Plan.deleteOne({_id: id}, (err) => {
        if (err) return res.send(err);
        Task.remove({plan: id}, (err, updatedPlan) => { if(err) {console.log(err)} })
        return res.json({success: true});
    });
});

router.post('/createTask', (req, res) => {
    let task = new Task();
    const {name, messageSender, planId } = req.body;
    //checks if plan with planId exists
   Plan.exists({_id: planId}, (err, exists) => {
        console.log(exists)
        if (!name || !messageSender || !planId || !exists ) {
            return res.json({
                success: false,
                error: 'INVALID INPUTS',
            });
        }

        task.name = name;
        task.lastTimeDone = 0;
        task.plan = planId;
        task.save((err, newtask) => {
            if (err) return res.json({success: false, error: err});
            Plan.updateOne(
                { _id: planId },
                {
                    $push: {
                        tasks: [{taskName: name, taskId: newtask._id}]
                    }
                },
                (err, updatedPlan) => { if(err) {console.log(err)} } )
            return res.json({success: true});
        });
   });
});


/*
    router.get('/getData', (req, res) => {
        Data.find((err, data) => {
            if (err) {
                return res.json({success: false, error: err});

            } else {
                return res.json({success: true, data: data});
            }
        });
    });

// this is our update method
// this method overwrites existing data in our database
    router.post('/updateData', (req, res) => {
        const {id, update} = req.body;
        eventEmitter.emit('update', 'update');
        Data.findByIdAndUpdate(id, update, (err) => {
            if (err) return res.json({success: false, error: err});
            return res.json({success: true});
        });
    });

// this is our delete method
// this method removes existing data in our database
    router.delete('/deleteData', (req, res) => {
        const {id} = req.body;
        eventEmitter.emit('update', 'delete');
        Data.findByIdAndRemove(id, (err) => {
            if (err) return res.send(err);
            return res.json({success: true});
        });
    });

// this is our create method
// this method adds new data in our database
    router.post('/putData', (req, res) => {
        let data = new Data();
        eventEmitter.emit('update', 'post');
        const {id, message} = req.body;

        if ((!id && id !== 0) || !message) {
            return res.json({
                success: false,
                error: 'INVALID INPUTS',
            });
        }
        data.message = message;
        data.id = id;
        data.save((err) => {
            if (err) return res.json({success: false, error: err});
            return res.json({success: true});
        });
    });

    */

// append /api for our http requests
    app.use('/api', router);


// launch our backend into a port
    server.listen(API_PORT, () => console.log(`LISTENING ON PORT ${API_PORT}`));




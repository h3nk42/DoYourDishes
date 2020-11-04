const mongoose = require('mongoose');
const express = require('express');
let cors = require('cors');
const bodyParser = require('body-parser');
const logger = require('morgan');
const Data = require('./data');
const http = require('http')
const socketIo = require('socket.io')

const events = require('events');


const password = require('./pw/pw.json');


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

// append /api for our http requests
    app.use('/api', router);


// launch our backend into a port
    server.listen(API_PORT, () => console.log(`LISTENING ON PORT ${API_PORT}`));




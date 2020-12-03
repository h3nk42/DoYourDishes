//
const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const TaskSchema = new Schema(
    {
        name: {
            type: String,
            required: false
        },
        lastTimeDone: {
            type: mongoose.Date,
            required: false
        },
        plan: {
            type: mongoose.ObjectId,
            required: false
        },
    },
    { timestamps: true }
);

// export the new Schema so we could modify it using Node.js
module.exports = mongoose.model("Task", TaskSchema);

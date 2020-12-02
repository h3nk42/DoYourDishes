//
const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const TaskSchema = new Schema(
    {
        name: String,
        lastTimeDone: Number,
        plan: mongoose.Schema.Types.ObjectId,
    },
    { timestamps: true }
);

// export the new Schema so we could modify it using Node.js
module.exports = mongoose.model("Task", TaskSchema);

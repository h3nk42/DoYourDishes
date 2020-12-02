//
const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const PlanSchema = new Schema(
    {   owner: String,
        name: String,
        users: [],
        tasks: [],
    },
    { timestamps: true }
);

// export the new Schema so we could modify it using Node.js
module.exports = mongoose.model("Plan", PlanSchema, "Plan");

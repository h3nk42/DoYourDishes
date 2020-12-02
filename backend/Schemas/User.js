//
const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const UserSchema = new Schema(
    {
        name: {
            type: 'String',
            required: true,
            trim: true,
            unique: true
        },
        password: {
            type: 'String',
            required: true,
            trim: true
        },
        plan: mongoose.Schema.Types.ObjectId
    },
    { timestamps: true }
);

// export the new Schema so we could modify it using Node.js
module.exports = mongoose.model("User", UserSchema);

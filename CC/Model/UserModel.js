const Sequelize = require("sequelize");
const db = require("../config/db");

const User = db.define(
    "users",
    {
        userID: {type: Sequelize.INTEGER, primaryKey: true, autoIncrement: true},
        name: {type: Sequelize.STRING, allowNull: false},
        email: {type: Sequelize.STRING, allowNull: false},
        password: {type: Sequelize.STRING, allowNull: false},
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = User;
const User = require("../Model/UserModel");
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');

require('dotenv').config();

const register =  async(req, res, next) => {
    try {
        const {
            name,
            email,
            passwords,
        } = req.body

        if (!(name && email && passwords)) {
            res.status(400).send("All input is required");
          }

          const CheckUser = await User.findOne({  where: {email: email} });

          if (CheckUser) {
            return res.status(409).send("User Already Exist. Please Login");
          }

        var salt = bcrypt.genSaltSync(10);
        var  password  = bcrypt.hashSync(passwords, salt);
        const newUser = new User ({
            name,
            email,
            password,
        })

        await newUser.save();
        res.json({
            status:'true',
            message: 'success', 
            newUser
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
};


const login =  async(req, res, next) => {
    try {
        const {
            email,
            password,
        } = req.body

        const Cekuser = await User.findOne({
            where: {email: email}
        })

        if (!(email && password)) {
            res.status(400).send("All input is required");
          }

        if (!Cekuser){
            return res.status(404).send("User Not found.");
        }

        const passwordIsValid = bcrypt.compareSync(password, Cekuser.password);

          if (!passwordIsValid) {
            return res.status(401).send({
              message: "Invalid Password!",
            });
          }

          const token = jwt.sign({ userID: Cekuser.userID }, process.env.JWT_KEY, { expiresIn: 86400 });
          res.json({
            status:'true',
            message: 'success', 
            token
        });
        
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
};

/// tes comit
module.exports = {
    register,
    login
}
const jwt = require("jsonwebtoken");
require('dotenv').config();

const config = process.env;

const verifyToken = (req, res, next) => {
  const token =
    req.body.token || req.query.token || req.headers["x-access-token"];

  if (!token) {
    return res.status(403).res.json({
        status:'false',
        message: 'Token is required',
    });
  }
  try {
    const decoded = jwt.verify(token, config.JWT_KEY);
    req.user = decoded;
  } catch (err) {
    return res.status(401).res.json({
        status:'false',
        message: 'Invalid Token'
    });
  }
  return next();
};

module.exports = verifyToken;
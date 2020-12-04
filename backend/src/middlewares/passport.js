import { Strategy, ExtractJwt } from 'passport-jwt';
import { config, underscoreId } from './config';
import User from '../model/User';
require('dotenv').config();

const config = {
    passport: {
        secret: process.env.JWT_SECRET,
        expiresIn: 10000,
    }
}

const underscoreId = '_id';

export const applyPassportStrategy = passport => {
    const options = {};
    options.jwtFromRequest = ExtractJwt.fromAuthHeaderAsBearerToken();
    options.secretOrKey = config.passport.secret;
    passport.use(
        new Strategy(options, (payload, done) => {
            User.findOne({ userName: payload.userName }, (err, user) => {
                if (err) return done(err, false);
                if (user) {
                    return done(null, {
                        userName: user.userName,
                        _id: user[underscoreId]
                    });
                }
                return done(null, false);
            });
        })
    );
};

// Use this hook to manipulate incoming or outgoing data.
// For more information on hooks see: http://docs.feathersjs.com/api/hooks.html

const feathers = require('@feathersjs/feathers');
const jsonStringify = require('json-stringify-safe');
const logger = require('winston');

const rest = require('../_helper/oauth-icovn-rest-client');

// eslint-disable-next-line no-unused-vars
module.exports = function (options = {}) {
  return async context => {
    logger.info('context %s', jsonStringify(context));
    const { app, data } = context;

    if (context.params.oauth) {
      // do something for all OAuth providers
    }

    if (context.params.oauth && context.params.oauth.provider === 'icovn') {
      // do something specific to the icovn provider
      const profile = await rest.getProfile(data.icovn.accessToken);
      // logger.info('profile %s', jsonStringify(profile));
      data.email = profile.data.name;
      data.source = 'oauth2-icovn';

      const dbUser = await app.service('users').find({
        query: {
          email: data.email
        }
      });
      logger.info('dbUser %s', jsonStringify(dbUser));

      if(dbUser.total > 0){
        context.result = "SKIP INSERT NEW USER";
      }
    }

    return context;
  };
};

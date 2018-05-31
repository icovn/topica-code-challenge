// Use this hook to manipulate incoming or outgoing data.
// For more information on hooks see: http://docs.feathersjs.com/api/hooks.html

const rest = require('../_helper/oauth-icovn-rest-client');

// eslint-disable-next-line no-unused-vars
module.exports = function (options = {}) {
  return async context => {

    if (context.params.oauth) {
      // do something for all OAuth providers
    }

    if (context.params.oauth && context.params.oauth.provider === 'icovn') {
      // do something specific to the icovn provider

      const message = context.data;

      const email = await rest.getProfile(message.icovn.accessToken);

      message.email = email.data.name;
      message.source = 'oauth2-icovn';

    }
    return context;
  };
};

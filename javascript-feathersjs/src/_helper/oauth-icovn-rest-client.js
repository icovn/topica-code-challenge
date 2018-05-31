const logger = require('winston');
const axios = require('axios');

module.exports.getProfile = async function getProfile(token) {
  try {
    return await axios({
      method: 'get',
      url: 'http://sso.icovn.me/user/me',
      headers: {
        'Authorization': 'Bearer ' + token
      },
    });
  } catch (error) {
    logger.error(error);
  }
}

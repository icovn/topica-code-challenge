const assert = require('assert');
const feathers = require('@feathersjs/feathers');
const enrichOauth2User = require('../../src/hooks/enrich-oauth-2-user');

describe('\'enrich-oauth2-user\' hook', () => {
  let app;

  beforeEach(() => {
    app = feathers();

    app.use('/dummy', {
      async get(id) {
        return { id };
      }
    });

    app.service('dummy').hooks({
      before: enrichOauth2User()
    });
  });

  it('runs the hook', async () => {
    const result = await app.service('dummy').get('test');
    
    assert.deepEqual(result, { id: 'test' });
  });
});

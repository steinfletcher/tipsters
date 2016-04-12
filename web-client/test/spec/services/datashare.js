'use strict';

describe('Service: datashare', function () {

  // load the service's module
  beforeEach(module('tipstersApp'));

  // instantiate service
  var datashare;
  beforeEach(inject(function (_datashare_) {
    datashare = _datashare_;
  }));

  it('should do something', function () {
    expect(!!datashare).toBe(true);
  });

});

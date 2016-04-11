'use strict';

describe('Service: slipGeneration', function () {

  // load the service's module
  beforeEach(module('tipstersApp'));

  // instantiate service
  var slipGeneration;
  beforeEach(inject(function (_slipGeneration_) {
    slipGeneration = _slipGeneration_;
  }));

  it('should do something', function () {
    expect(!!slipGeneration).toBe(true);
  });

});

'use strict';

describe('Service: dataRetrieval', function () {

  // load the service's module
  beforeEach(module('tipstersApp'));

  // instantiate service
  var dataRetrieval;
  beforeEach(inject(function (_dataRetrieval_) {
    dataRetrieval = _dataRetrieval_;
  }));

  it('should do something', function () {
    expect(!!dataRetrieval).toBe(true);
  });

});

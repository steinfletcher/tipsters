'use strict';

describe('Controller: CustomctrlCtrl', function () {

  // load the controller's module
  beforeEach(module('tipstersApp'));

  var CustomctrlCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CustomctrlCtrl = $controller('CustomctrlCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CustomctrlCtrl.awesomeThings.length).toBe(3);
  });
});

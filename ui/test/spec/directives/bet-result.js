'use strict';

describe('Directive: betResult', function () {

  // load the directive's module
  beforeEach(module('tipstersApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<bet-result></bet-result>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the betResult directive');
  }));
});

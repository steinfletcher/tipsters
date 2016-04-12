'use strict';

/**
 * @ngdoc service
 * @name tipstersApp.slipGeneration
 * @description
 * # slipGeneration -
 *
 * As the system has no outside knowledge of football (pundit tips), the assumption will be to always suggest the favourite for each match.
 * Potential users would prefer to back several favorites rather than a single outsider with equivalent odds.
 *
 * Service in the tipstersApp.
 */
angular.module('tipstersApp')
  .service('slipGeneration', function () {
    var api = {};
    var range = 0.5;

    /**
     * Generate slip for given competitions and targetOdds
     * @param competitions
     * @param targetOdds
     * @returns {*}
       */
    api.generateSlip = function (competitions, targetOdds) {
      //rearrange matches into single ordered array
      return buildSlip(returnLowestOddsArray(competitions), targetOdds);
    };

    function buildSlip(orderedMatches, targetOdds) {
      var slip = [];
      _.every(orderedMatches, function (match) {
        //set the selection
        match.selection = findLowestOdds(match);
        slip.push(match);
        //check if target odds have been achieved
        if (api.calculateSlipOdds(slip) >= targetOdds) {
          return false;
        }
        return true;
      });
      //check if the slip is acceptable
      if (oddsWithinRange(api.calculateSlipOdds(slip), targetOdds)) {
        return slip;
      } else {
        //refine slip
        var refinedSlip = refineSlip(slip, targetOdds);
        return returnMostAccurate(slip, refinedSlip, targetOdds);
      }
    }

    /**
     * remove lowest risk bets to move closer to target odds
     * @param slip
     * @param targetOdds
     * @returns {*}
       */
    function refineSlip(slip, targetOdds) {
      var closestRefinedSlip = slip;
      _.every(slip, function (match) {
        var potentialSlip = _.without(slip, match);
        closestRefinedSlip = returnMostAccurate(closestRefinedSlip, potentialSlip, targetOdds);
        if (oddsWithinRange(api.calculateSlipOdds(potentialSlip), targetOdds) || api.calculateSlipOdds(potentialSlip) <= targetOdds) {
          return false;
        }
        return true;
      });

      return closestRefinedSlip;
    }

    /**
     * Compare both slips and return most accurate
     * @param slip1
     * @param slip2
     * @param requestedOdds
     * @returns {*}
     */
    function returnMostAccurate(slip1, slip2, requestedOdds) {
      var slip1Difference = Math.abs(requestedOdds - api.calculateSlipOdds(slip1));
      var slip2Difference = Math.abs(requestedOdds - api.calculateSlipOdds(slip2));

      if (slip1Difference > slip2Difference) {
        //set new bet line
        return slip2;
      } else {
        return slip1;
      }
    }

    /**
     * check the suggested odds are within an acceptable range
     * @param odds
     * @param requestedOdds
     * @returns {boolean}
     */
    function oddsWithinRange(odds, requestedOdds) {
      if ((Math.abs(odds - requestedOdds) < range)) {
        return true;
      } else {
        return false;
      }
    }

    /**
     * Calculate the slips odds
     * @param slip
     * @returns {number}
     */
    api.calculateSlipOdds = function(slip) {
      var odds = 1;
      _.each(slip, function (match) {
        odds *= findLowestOdds(match).oddsDecimal;
      });
      return odds;
    };

    /**
     *  returns an array of matches sorted by lowest odds
     * @param matches
     */
    function returnLowestOddsArray(competitions) {
      //'pluck' out the matches into one array
      var allMatches = _.flatten(_.map(competitions, 'matches'));
      var orderedMatches = _.sortBy(allMatches, function (match) {
        return findLowestOdds(match).oddsDecimal;
      });

      return orderedMatches;
    }

    /**
     * find the lowest odds out of win, lose, draw for a given match
     *
     * @param match
     * @returns {*}
     */
    function findLowestOdds(match) {
      return _.minBy(match.bets, 'oddsDecimal');
    }

    return api;
  });

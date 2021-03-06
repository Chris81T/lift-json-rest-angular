angular.module("ngLocale", [], ["$provide", function ($provide) {
    var PLURAL_CATEGORY = {ZERO: "zero", ONE: "one", TWO: "two", FEW: "few", MANY: "many", OTHER: "other"};
    $provide.value("$locale", {"NUMBER_FORMATS": {"DECIMAL_SEP": ".", "GROUP_SEP": ",", "PATTERNS": [
        {"minInt": 1, "minFrac": 0, "macFrac": 0, "posPre": "", "posSuf": "", "negPre": "-", "negSuf": "", "gSize": 3, "lgSize": 3, "maxFrac": 3},
        {"minInt": 1, "minFrac": 2, "macFrac": 0, "posPre": "\u00A4 ", "posSuf": "", "negPre": "\u00A4 -", "negSuf": "", "gSize": 3, "lgSize": 3, "maxFrac": 2}
    ], "CURRENCY_SYM": "F"}, "pluralCat": function (n) {
        if (n == 0 || n == 1) {
            return PLURAL_CATEGORY.ONE;
        }
        return PLURAL_CATEGORY.OTHER;
    }, "DATETIME_FORMATS": {"MONTH": ["sánzá ya yambo", "sánzá ya míbalé", "sánzá ya mísáto", "sánzá ya mínei", "sánzá ya mítáno", "sánzá ya motóbá", "sánzá ya nsambo", "sánzá ya mwambe", "sánzá ya libwa", "sánzá ya zómi", "sánzá ya zómi na mɔ̌kɔ́", "sánzá ya zómi na míbalé"], "SHORTMONTH": ["s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12"], "DAY": ["eyenga", "mokɔlɔ ya libosó", "mokɔlɔ ya míbalé", "mokɔlɔ ya mísáto", "mokɔlɔ ya mínéi", "mokɔlɔ ya mítáno", "mpɔ́sɔ"], "SHORTDAY": ["eye", "m1", "m2", "m3", "m4", "m5", "mps"], "AMPMS": ["AM", "PM"], "medium": "y MMM d HH:mm:ss", "short": "yy/MM/dd HH:mm", "fullDate": "EEEE, y MMMM dd", "longDate": "y MMMM d", "mediumDate": "y MMM d", "shortDate": "yy/MM/dd", "mediumTime": "HH:mm:ss", "shortTime": "HH:mm"}, "id": "ln-cd"});
}]);
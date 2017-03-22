function(e) {
    isDate: function(e) {
        return "Date" == t.typeName(e)
    },
    isArray: function(e) {
        return "Array" == t.typeName(e)
    },
    isDebug: function() {
        return e.tools.debug
    },
    stringify: function(e) {
        return window.JSON3 ? window.JSON3.stringify(e) : JSON.stringify(e)
    },
    isNumber: function(e) {
        return "number" == typeof e || e instanceof Number
    },
} (window.plus),
function(e) {
    var e = e,
    e.runtime = {
        quit: function() {
            alert("quit");
        },
    }
} (window.plus),


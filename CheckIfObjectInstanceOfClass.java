/**
 * @param {*} obj
 * @param {*} classFunction
 * @return {boolean}
 */
var checkIfInstanceOf = function(obj, classFunction) {
    if (obj == null || typeof classFunction !== "function") {
        return false;
    }

    if (classFunction === Object) {
        return true;
    }

    if (classFunction === Number) {
        return typeof obj === "number" || obj instanceof Number;
    }

    if (classFunction === String) {
        return typeof obj === "string" || obj instanceof String;
    }

    if (classFunction === Boolean) {
        return typeof obj === "boolean" || obj instanceof Boolean;
    }

    if (classFunction === BigInt) {
        return typeof obj === "bigint";
    }

    if (classFunction === Symbol) {
        return typeof obj === "symbol";
    }

    return obj instanceof classFunction;
};

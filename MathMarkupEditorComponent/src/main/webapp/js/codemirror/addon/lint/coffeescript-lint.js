/**
* Copyright (c) 2016, University of South Africa and/or its affiliates. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This code is free software; you can redistribute it and/or modify it
* under the terms of the GNU General Public License version 2 only, as
* published by the Free Software Foundation.
*
* This code is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
* FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
* version 2 for more details (a copy is included in the LICENSE file that
* accompanied this code).
*
* You should have received a copy of the GNU General Public License version
* 2 along with this work; if not, write to the Free Software Foundation,
* Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
*
* @author      Mufaro Benedict
* @version     1.0 
**/


// Depends on coffeelint.js from http://www.coffeelint.org/js/coffeelint.js

// declare global: coffeelint

(function(mod) {
  if (typeof exports == "object" && typeof module == "object") // CommonJS
    mod(require("../../lib/codemirror"));
  else if (typeof define == "function" && define.amd) // AMD
    define(["../../lib/codemirror"], mod);
  else // Plain browser env
    mod(CodeMirror);
})(function(CodeMirror) {
"use strict";

CodeMirror.registerHelper("lint", "coffeescript", function(text) {
  var found = [];
  var parseError = function(err) {
    var loc = err.lineNumber;
    found.push({from: CodeMirror.Pos(loc-1, 0),
                to: CodeMirror.Pos(loc, 0),
                severity: err.level,
                message: err.message});
  };
  try {
    var res = coffeelint.lint(text);
    for(var i = 0; i < res.length; i++) {
      parseError(res[i]);
    }
  } catch(e) {
    found.push({from: CodeMirror.Pos(e.location.first_line, 0),
                to: CodeMirror.Pos(e.location.last_line, e.location.last_column),
                severity: 'error',
                message: e.message});
  }
  return found;
});

});

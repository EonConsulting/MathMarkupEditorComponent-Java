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


(function(mod) {
  if (typeof exports == "object" && typeof module == "object") // CommonJS
    mod(require("../../lib/codemirror"));
  else if (typeof define == "function" && define.amd) // AMD
    define(["../../lib/codemirror"], mod);
  else // Plain browser env
    mod(CodeMirror);
})(function(CodeMirror) {
"use strict";

// Depends on js-yaml.js from https://github.com/nodeca/js-yaml

// declare global: jsyaml

CodeMirror.registerHelper("lint", "yaml", function(text) {
  var found = [];
  try { jsyaml.load(text); }
  catch(e) {
      var loc = e.mark;
      found.push({ from: CodeMirror.Pos(loc.line, loc.column), to: CodeMirror.Pos(loc.line, loc.column), message: e.message });
  }
  return found;
});

});

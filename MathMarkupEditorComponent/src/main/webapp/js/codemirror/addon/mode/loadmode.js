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
    mod(require("../../lib/codemirror"), "cjs");
  else if (typeof define == "function" && define.amd) // AMD
    define(["../../lib/codemirror"], function(CM) { mod(CM, "amd"); });
  else // Plain browser env
    mod(CodeMirror, "plain");
})(function(CodeMirror, env) {
  if (!CodeMirror.modeURL) CodeMirror.modeURL = "../mode/%N/%N.js";

  var loading = {};
  function splitCallback(cont, n) {
    var countDown = n;
    return function() { if (--countDown == 0) cont(); };
  }
  function ensureDeps(mode, cont) {
    var deps = CodeMirror.modes[mode].dependencies;
    if (!deps) return cont();
    var missing = [];
    for (var i = 0; i < deps.length; ++i) {
      if (!CodeMirror.modes.hasOwnProperty(deps[i]))
        missing.push(deps[i]);
    }
    if (!missing.length) return cont();
    var split = splitCallback(cont, missing.length);
    for (var i = 0; i < missing.length; ++i)
      CodeMirror.requireMode(missing[i], split);
  }

  CodeMirror.requireMode = function(mode, cont) {
    if (typeof mode != "string") mode = mode.name;
    if (CodeMirror.modes.hasOwnProperty(mode)) return ensureDeps(mode, cont);
    if (loading.hasOwnProperty(mode)) return loading[mode].push(cont);

    var file = CodeMirror.modeURL.replace(/%N/g, mode);
    if (env == "plain") {
      var script = document.createElement("script");
      script.src = file;
      var others = document.getElementsByTagName("script")[0];
      var list = loading[mode] = [cont];
      CodeMirror.on(script, "load", function() {
        ensureDeps(mode, function() {
          for (var i = 0; i < list.length; ++i) list[i]();
        });
      });
      others.parentNode.insertBefore(script, others);
    } else if (env == "cjs") {
      require(file);
      cont();
    } else if (env == "amd") {
      requirejs([file], cont);
    }
  };

  CodeMirror.autoLoadMode = function(instance, mode) {
    if (!CodeMirror.modes.hasOwnProperty(mode))
      CodeMirror.requireMode(mode, function() {
        instance.setOption("mode", instance.getOption("mode"));
      });
  };
});

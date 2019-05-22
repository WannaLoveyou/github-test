(function(window){var svgSprite='<svg><symbol id="icon-mima1" viewBox="0 0 1024 1024"><path d="M836.778667 984.234667H187.221333C131.072 984.234667 85.333333 941.397333 85.333333 888.234667V480.768C85.333333 427.690667 130.56 384.853333 187.221333 384.853333h25.088V289.877333C212.309333 125.013333 346.624 0 512 0s299.690667 125.013333 299.690667 289.877333v95.488h25.088c56.149333 0 101.888 42.752 101.888 95.914667v407.466667c0 52.650667-45.738667 95.488-101.888 95.488zM469.162667 681.557333V788.48c0 22.186667 19.2 40.362667 42.837333 40.362667s42.837333-18.261333 42.837333-40.362667V681.557333a79.530667 79.530667 0 0 0 42.752-69.888c0-44.714667-38.4-81.152-85.589333-81.152-47.274667 0-85.589333 36.437333-85.589333 81.152 0 30.037333 17.152 56.149333 42.752 69.973334z m256.853333-394.666666C726.101333 175.189333 630.186667 84.138667 512 84.138667S297.898667 175.189333 297.898667 286.890667V384.853333H726.186667V286.890667z" fill="#5283d8" ></path></symbol><symbol id="icon-wode_1" viewBox="0 0 1024 1024"><path d="M907.264 966.656H114.688c-16.384 0-32.768-8.192-43.008-20.48-10.24-14.336-14.336-30.72-10.24-47.104 40.96-208.896 210.944-364.544 415.744-380.928 68.608 0 142.848 8.704 217.088 38.912 21.504 8.704 28.672 14.336 40.96 20.48 118.784 67.584 200.704 184.32 227.328 321.536 4.096 16.384 0 32.768-12.288 47.104-10.24 12.288-26.624 20.48-43.008 20.48z" fill="#E31D1A" ></path><path d="M115.712 933.376c-6.656 0-12.8-2.56-16.384-7.168-0.512-0.512-0.512-1.024-1.024-1.536-5.12-5.12-5.632-13.824-4.608-18.944 37.888-194.56 197.12-340.48 387.584-354.304h15.36c91.648 0 179.712 28.672 222.72 55.808 112.64 67.584 187.904 173.568 211.456 298.496 1.024 7.168-0.512 13.824-5.632 20.48-3.584 4.608-9.728 7.168-16.384 7.168H115.712z" fill="#E31D1A" ></path><path d="M496.64 563.712c89.088 0 174.592 27.648 216.064 53.76h0.512c109.568 65.024 182.272 167.936 204.8 289.792 0.512 2.56 0.512 6.144-3.584 10.752-0.512 1.024-3.072 2.048-6.144 2.048H115.712c-3.072 0-5.632-1.024-6.144-2.048-0.512-1.024-1.536-2.048-2.56-2.56-1.024-1.024-2.048-4.096-1.024-7.68 36.864-188.928 190.976-330.24 375.808-344.064 4.608 0.512 9.728 0 14.848 0m0-25.6c-5.12 0-10.752 0-15.872 0.512-196.608 14.336-360.448 163.84-399.36 364.544-2.048 10.24 0 22.528 8.192 30.72 6.144 8.192 16.384 12.288 26.624 12.288h792.576c10.24 0 20.48-4.096 26.624-12.288 6.144-8.192 10.24-18.432 8.192-30.72-24.576-131.072-102.4-238.592-217.088-307.2-43.52-27.136-132.608-57.856-229.888-57.856z" fill="#E31D1A" ></path><path d="M512 536.576c-129.024 0-235.52-106.496-235.52-235.52s106.496-235.52 235.52-235.52 235.52 106.496 235.52 235.52-106.496 235.52-235.52 235.52z" fill="#E31D1A" ></path><path d="M512 503.296c-111.616 0-202.24-90.624-202.24-202.24S400.384 98.816 512 98.816s202.24 90.624 202.24 202.24-90.624 202.24-202.24 202.24z" fill="#E31D1A" ></path><path d="M512 111.616c104.448 0 189.44 84.992 189.44 189.44s-84.992 189.44-189.44 189.44-189.44-84.992-189.44-189.44 84.992-189.44 189.44-189.44m0-25.6c-118.784 0-215.04 96.256-215.04 215.04s96.256 215.04 215.04 215.04 215.04-96.256 215.04-215.04-96.256-215.04-215.04-215.04z" fill="#E31D1A" ></path></symbol></svg>';var script=function(){var scripts=document.getElementsByTagName("script");return scripts[scripts.length-1]}();var shouldInjectCss=script.getAttribute("data-injectcss");var ready=function(fn){if(document.addEventListener){if(~["complete","loaded","interactive"].indexOf(document.readyState)){setTimeout(fn,0)}else{var loadFn=function(){document.removeEventListener("DOMContentLoaded",loadFn,false);fn()};document.addEventListener("DOMContentLoaded",loadFn,false)}}else if(document.attachEvent){IEContentLoaded(window,fn)}function IEContentLoaded(w,fn){var d=w.document,done=false,init=function(){if(!done){done=true;fn()}};var polling=function(){try{d.documentElement.doScroll("left")}catch(e){setTimeout(polling,50);return}init()};polling();d.onreadystatechange=function(){if(d.readyState=="complete"){d.onreadystatechange=null;init()}}}};var before=function(el,target){target.parentNode.insertBefore(el,target)};var prepend=function(el,target){if(target.firstChild){before(el,target.firstChild)}else{target.appendChild(el)}};function appendSvg(){var div,svg;div=document.createElement("div");div.innerHTML=svgSprite;svgSprite=null;svg=div.getElementsByTagName("svg")[0];if(svg){svg.setAttribute("aria-hidden","true");svg.style.position="absolute";svg.style.width=0;svg.style.height=0;svg.style.overflow="hidden";prepend(svg,document.body)}}if(shouldInjectCss&&!window.__iconfont__svg__cssinject__){window.__iconfont__svg__cssinject__=true;try{document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")}catch(e){console&&console.log(e)}}ready(appendSvg)})(window)
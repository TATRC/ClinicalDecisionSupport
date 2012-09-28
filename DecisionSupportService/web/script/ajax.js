
function callServer(url, callback, allowCache)
{
  if ((allowCache == null) || (allowCache == false))
    url = avoidCache(url);

  var http = getHttpObject();
  http.onreadystatechange = function ()
  {
    if ((http.readyState == 4) && (callback != null))
      callback(http.responseText, http);
  };
  http.open("GET", url, true);
  http.send(null);
}

function avoidCache(url)
{
  if (url.indexOf("?") > 0)
    return url + "&r=" + new Date().getTime();
  else
    return url + "?r=" + new Date().getTime();
}

function getHttpObject()
{
  var xmlhttp=false;

  /*@cc_on @*/
  /*@if (@_jscript_version >= 5)
  // JScript gives us Conditional compilation, we can cope with old IE versions.
  // and security blocked creation of the objects.
  try
  {
    xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
  }
  catch (e)
  {
    try
    {
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    catch (E)
    {
      xmlhttp = false;
    }
  }
  @end @*/

  if (!xmlhttp && typeof XMLHttpRequest!='undefined')
  {
    try
    {
      xmlhttp = new XMLHttpRequest();
    }
    catch (e)
    {
      xmlhttp = false;
    }
  }
  if (!xmlhttp && window.createRequest)
  {
    try
    {
      xmlhttp = window.createRequest();
    }
    catch (e)
    {
      xmlhttp = false;
    }
  }

  return xmlhttp;
}

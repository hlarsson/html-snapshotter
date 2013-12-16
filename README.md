html-snapshotter
================

Tool to create a html snapshot of a dynamically loaded web site.

Do a GET request with the following parameters to get a html snapshot of the page
* url - The base url of the site, e.g. http://www.somesite.com/site/
* fragment - The hash fragment of the page to get the snapshot for, e.g. main

So going to http://www.htmlsnapshotterhost.com/HtmlSnapshotter?url=http://www.somesite.com/site/&fragment=main will make a request to http://www.somesite.com/site/#!main and return a html snapshot of that page.

# DS/2 OSS Core WebTools

Some filters and listeners that can be useful in web applications.

## Slf4jRequestFilter

This filter is meant to add some logging data on every web request. Especially to add some request data like

* origin ip address
* request url
* session id
* http request method
* ...

All this information is added via SLF4Js MDC system to any underlying logging solution. Current logging systems
that use MDC so far are

* Logback
* Log4j 1.x
* Log4j 2.x

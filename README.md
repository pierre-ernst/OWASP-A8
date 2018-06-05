# OWASP Top 10 - A8

Sample code to demonstrate the [Top 10-2017 A8-Insecure Deserialization](https://www.owasp.org/index.php/Top_10-2017_A8-Insecure_Deserialization) vulnerability class.

This simple web application deserializes user-supplied data. The expected behavior is that users are going to submit base64 encoded serialized instances of the [BicyleBean](./src/main/java/org/owasp/ottawa/topten2017_A8/BicycleBean.java) class.

## Warning
**Running this web application will expose you to a serious remote code execution vulnerability. Do not expose the application server and only run it locally, behind a firewall or inside a network detached virtual machine.**

## Instalation:
```bash
$ mvn install
```

## Usage:
```bash
$ mvn jetty:run
```
Go to the [http://127.0.0.1:666/](http://127.0.0.1:666/) URL


pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        jcenter()
    }
}

rootProject.name = "ds2-oss-core-project"
include("api")
include("base")
include("interceptors")
include("base-it")
include("es")
include("es-test")
include("options")
include("infinispan")
include("options-it")
include("crypto")
include("codec-boon")
include("codec-gson")
include("codec-fastxml")
include("webtools")
include("xmpp")
include("xmpp-gcm")
include("test-ear")
include("es-apt")
include("testutils")
include("dbtools")
include("crypto-bc")
include("dbtools-it")
include(":jee:rest")
include("statics")
include("math")
include("abstracts")
include("hazelcast")
include("codec-genson")
include("owbtest")
include("shiro-jee")
include("finance")
include(":jee:jwt")

project(":api").name = "ds2-oss-core-api"
project(":base").name = "ds2-oss-core-base"
project(":interceptors").name = "ds2-oss-core-interceptors"
project(":base-it").name = "ds2-oss-core-base-it"
project(":es").name = "ds2-oss-core-elasticsearch"
project(":es-test").name = "ds2-oss-core-elasticsearch-test"
project(":options").name = "ds2-oss-core-options"
project(":infinispan").name = "ds2-oss-core-infinispan"
project(":options-it").name = "ds2-oss-core-options-it"
project(":crypto").name = "ds2-oss-core-crypto"
project(":codec-boon").name = "ds2-oss-core-codec-boon"
project(":codec-gson").name = "ds2-oss-core-codec-gson"
project(":codec-fastxml").name = "ds2-oss-core-codec-fastxml"
project(":webtools").name = "ds2-oss-core-webtools"
project(":xmpp").name = "ds2-oss-core-xmpp"
project(":xmpp-gcm").name = "ds2-oss-core-xmpp-gcm"
project(":test-ear").name = "ds2-oss-core-test-ear"
project(":es-apt").name = "ds2-oss-core-elasticsearch-annotationprocessing"
project(":testutils").name = "ds2-oss-core-testutils"
project(":dbtools").name = "ds2-oss-core-dbtools"
project(":crypto-bc").name = "ds2-oss-core-crypto-bouncycastle"
project(":dbtools-it").name = "ds2-oss-core-dbtools-it"
project(":jee:rest").name = "ds2-oss-core-jee-rest"
project(":jee:jwt").name = "ds2-oss-core-jee-jwt"
project(":statics").name = "ds2-oss-core-statics"
project(":math").name = "ds2-oss-core-math"
project(":abstracts").name = "ds2-oss-core-abstracts"
project(":hazelcast").name = "ds2-oss-core-grid-hazelcast"
project(":codec-genson").name = "ds2-oss-core-codec-genson"
project(":owbtest").name = "ds2-oss-core-owb-test"
project(":shiro-jee").name = "ds2-oss-core-shiro-jee"
project(":finance").name = "ds2-oss-core-finance"

//enableFeaturePreview("STABLE_PUBLISHING")
include("csv")
project(":csv").name = "ds2-oss-core-csv"
include("thymeleaf")
project(":thymeleaf").name="ds2-oss-core-thymeleaf"

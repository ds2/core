
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "ds2-oss-core-project"
include("api")
include("base")
include("interceptors")
include("options")
include("crypto")
include("codec-boon")
include("codec-gson")
include("codec-fastxml")
include("webtools")
include("testutils")
include("dbtools")
include("crypto-bc")
include(":jee:rest")
include("statics")
include("math")
include("abstracts")
include("codec-genson")
include("shiro-jee")
include("finance")
include(":jee:jwt")

project(":api").name = "ds2-oss-core-api"
project(":base").name = "ds2-oss-core-base"
project(":interceptors").name = "ds2-oss-core-interceptors"
project(":options").name = "ds2-oss-core-options"
project(":crypto").name = "ds2-oss-core-crypto"
project(":codec-boon").name = "ds2-oss-core-codec-boon"
project(":codec-gson").name = "ds2-oss-core-codec-gson"
project(":codec-fastxml").name = "ds2-oss-core-codec-fastxml"
project(":webtools").name = "ds2-oss-core-webtools"
project(":testutils").name = "ds2-oss-core-testutils"
project(":dbtools").name = "ds2-oss-core-dbtools"
project(":crypto-bc").name = "ds2-oss-core-crypto-bouncycastle"
project(":jee:rest").name = "ds2-oss-core-jee-rest"
project(":jee:jwt").name = "ds2-oss-core-jee-jwt"
project(":statics").name = "ds2-oss-core-statics"
project(":math").name = "ds2-oss-core-math"
project(":abstracts").name = "ds2-oss-core-abstracts"
project(":codec-genson").name = "ds2-oss-core-codec-genson"
project(":shiro-jee").name = "ds2-oss-core-shiro-jee"
project(":finance").name = "ds2-oss-core-finance"

//enableFeaturePreview("STABLE_PUBLISHING")
include("csv")
project(":csv").name = "ds2-oss-core-csv"
include("thymeleaf")
project(":thymeleaf").name="ds2-oss-core-thymeleaf"

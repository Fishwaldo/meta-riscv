require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "VisionFive dev kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"

# pin srcrev for now to have a fixed target
# release VF2_v2.11.5
SRCREV:visionfive2 = "d9eee31aaec51ade1641391836c1f07dd2151a4a"
SRCREV:star64 = "1456c984f15e21e28fb8a9ce96d0ca10e61a71c4"
SRCREV:pinetabv = "1456c984f15e21e28fb8a9ce96d0ca10e61a71c4"

BRANCH = "visionfive"
BRANCH:visionfive2 = "JH7110_VisionFive2_devel"
BRANCH:star64 = "Star64_devel"
BRANCH:pinetabv = "Star64_devel"

FORK ?= "starfive-tech"
FORK:star64 ?= "Fishwaldo"
FORK:pinetabv ?= "Fishwaldo"

REPO ?= "linux"
REPO:star64 ?= "Star64_linux"
REPO:pinetabv ?= "Star64_linux"

SRC_URI = "git://github.com/${FORK}/${REPO}.git;protocol=https;branch=${BRANCH} \
           file://modules.cfg \
           "
SRC_URI:append:beaglev-starlight-jh7100 = " \
           file://extra.cfg \
"
SRC_URI:append:visionfive = " \
           file://extra.cfg \
"

SRC_URI:append:jh7110 = " \
           file://visionfive2-graphics.cfg \
"

LINUX_VERSION ?= "6.2.0"
LINUX_VERSION:visionfive2 = "5.15.0"
LINUX_VERSION:star64 = "5.15.131"
LINUX_VERSION:pinetabv = "5.15.131"
LINUX_VERSION_EXTENSION:append:beaglev-starlight-jh7100 = "-starlight"

KBUILD_DEFCONFIG:beaglev-starlight-jh7100 = "starfive_jh7100_fedora_defconfig"
KBUILD_DEFCONFIG:visionfive = "visionfive_defconfig"
KBUILD_DEFCONFIG:visionfive2 = "starfive_visionfive2_defconfig"
KBUILD_DEFCONFIG:star64 = "pine64_star64_defconfig"
KBUILD_DEFCONFIG:pinetabv = "pine64_pinetabv_defconfig"

COMPATIBLE_MACHINE = "(beaglev-starlight-jh7100|visionfive|jh7110)"

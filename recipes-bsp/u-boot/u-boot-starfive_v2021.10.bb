require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit uboot-extlinux-config

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

BRANCH:visionfive2 = "JH7110_VisionFive2_devel"
BRANCH:star64 = "Star64"
BRANCH:pinetabv = "Star64"

SRC_URI = "git://github.com/starfive-tech/u-boot.git;protocol=https;branch=${BRANCH} \
           file://tftp-mmc-boot.txt \
          "

SRC_URI:star64 = "git://github.com/Fishwaldo/u-boot.git;protocol=https;branch=${BRANCH} \
           file://tftp-mmc-boot.txt \
           file://uEnv-star64.txt \
          "

SRC_URI:pinetabv = "git://github.com/Fishwaldo/u-boot.git;protocol=https;branch=${BRANCH} \
           file://0001-add-initial-pinetabv-config.patch \
           file://tftp-mmc-boot.txt \
           file://uEnv-pinetabv.txt \
          "

SRC_URI:append:visionfive2 = " \
        file://uEnv-visionfive2.txt \
"


# tag VF2_v3.0.4
SRCREV:visionfive2 = "b6e2b0e85c774a18ae668223a6e5f7d335895243"
SRCREV:star64 = "172b47f62039605d6806fa96bd403c21cda28996"
SRCREV:pinetabv = "172b47f62039605d6806fa96bd403c21cda28996"

DEPENDS:append = " u-boot-tools-native"

DEPENDS:append:jh7110 = " jh7110-spl-tool-native"

# Overwrite this for your server
TFTP_SERVER_IP ?= "127.0.0.1"

do_configure:prepend() {
    sed -i -e 's,@SERVERIP@,${TFTP_SERVER_IP},g' ${WORKDIR}/tftp-mmc-boot.txt
    mkimage -O linux -T script -C none -n "U-Boot boot script" \
        -d ${WORKDIR}/tftp-mmc-boot.txt ${WORKDIR}/${UBOOT_ENV_BINARY}
}

do_deploy:append:visionfive2() {
    install -m 644 ${WORKDIR}/uEnv-visionfive2.txt ${DEPLOYDIR}/vf2_uEnv.txt
    spl_tool -c -f ${DEPLOYDIR}/${SPL_IMAGE}
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_SYMLINK}.normal.out
}

do_deploy:append:star64() {
    install -m 644 ${WORKDIR}/uEnv-star64.txt ${DEPLOYDIR}/vf2_uEnv.txt
    spl_tool -c -f ${DEPLOYDIR}/${SPL_IMAGE}
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_SYMLINK}.normal.out
}

do_deploy:append:pinetabv() {
    install -m 644 ${WORKDIR}/uEnv-pinetabv.txt ${DEPLOYDIR}/vf2_uEnv.txt
    spl_tool -c -f ${DEPLOYDIR}/${SPL_IMAGE}
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_SYMLINK}.normal.out
}


COMPATIBLE_MACHINE = "jh7110"

TOOLCHAIN = "gcc"


# U-boot sets O=... which needs it to build outside of S
B = "${WORKDIR}/build"

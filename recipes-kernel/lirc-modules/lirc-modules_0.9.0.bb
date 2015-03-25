SUMMARY = "Linux Infrared Remote Control"
DESCRIPTION = "LIRC is a package that allows you to decode and send infra-red signals of many commonly used remote controls."

HOMEPAGE = "http://www.lirc.org"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://prdownloads.sourceforge.net/lirc/lirc-${PV}.tar.bz2"
SRC_URI[md5sum] = "b232aef26f23fe33ea8305d276637086"
SRC_URI[sha256sum] = "6323afae6ad498d4369675f77ec3dbb680fe661bea586aa296e67f2e2daba4ff"

S = "${WORKDIR}/lirc-${PV}"

DEPENDS = "virtual/kernel"
#RDEPENDS_${PN} = "lirc-modules"

inherit autotools module

DRIVER ?= "--with-driver=devinput"
EXTRA_OECONF = "--with-kerneldir=${STAGING_KERNEL_DIR} ${DRIVER}"
PARALLEL_MAKE = ""
MAKE_TARGETS = "KERNEL_PATH=${STAGING_KERNEL_DIR} MAKE='make V=1' -C drivers"

fakeroot do_install() {
    oe_runmake -C drivers DESTDIR="${D}" moduledir="/lib/modules/${KERNEL_VERSION}/lirc" install
    rm -rf ${D}/dev
}

FILES_${PN} = "/lib/modules"

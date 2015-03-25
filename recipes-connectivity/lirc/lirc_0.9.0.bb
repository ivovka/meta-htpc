SUMMARY = "Linux Infrared Remote Control"
DESCRIPTION = "LIRC is a package that allows you to decode and send infra-red signals of many commonly used remote controls."
DESCRIPTION_append_lirc = " This package contains the lirc daemon, libraries and tools."
DESCRIPTION_append_lirc-x = " This package contains lirc tools for X11."
DESCRIPTION_append_lirc-exec = " This package contains a daemon that runs programs on IR signals."
DESCRIPTION_append_lirc-remotes = " This package contains some config files for remotes."

HOMEPAGE = "http://www.lirc.org"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://prdownloads.sourceforge.net/lirc/lirc-${PV}.tar.bz2"
SRC_URI[md5sum] = "b232aef26f23fe33ea8305d276637086"
SRC_URI[sha256sum] = "6323afae6ad498d4369675f77ec3dbb680fe661bea586aa296e67f2e2daba4ff"

S = "${WORKDIR}/lirc-${PV}"

DEPENDS = "virtual/kernel virtual/libx11 libxau libsm libice"
RDEPENDS_${PN} = "lirc-modules"
RDEPENDS_lirc-exec = "lirc"
RDEPENDS_lirc-x = "lirc"
RRECOMMENDS_lirc = "lirc-exec"

inherit autotools module-base

DRIVER ?= "--with-driver=devinput"
EXTRA_OECONF = "--with-kerneldir=${STAGING_KERNEL_DIR} ${DRIVER}"
PARALLEL_MAKE = ""

do_install_append() {
  install -d ${D}${datadir}/lirc/
  cp -pPR ${S}/remotes ${D}${datadir}/lirc/
  rm -rf ${D}/var
}

PACKAGES =+ "lirc-x lirc-exec lirc-remotes"
FILES_${PN}-dbg += "${bindir}/.debug ${sbindir}/.debug"
FILES_${PN}-dev += "${libdir}/liblirc_client.so"
FILES_lirc-exec = "${bindir}/irexec"
FILES_lirc-x = "${bindir}/irxevent ${bindir}/xmode2"
FILES_lirc-remotes = "${datadir}/lirc/remotes"
FILES_${PN} = "${bindir} ${sbindir} ${libdir}/lib*.so.* ${sysconfdir} ${exec_prefix}/var"

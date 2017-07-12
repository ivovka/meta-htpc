DESCRIPTION = "An utility to transform the XOrg into a daemon"
HOMEPAGE = "https://github.com/sofar/xorg-launch-helper"
PR = "r0"
SRCREV = "355c8229a4be86bb06e4cd59e9b7dd5e189393be"
PV = "+gitr${SRCPV}"
DEPENDS += "systemd"
RDEPENDS_${PN} = "xserver-xorg libsystemd"
REQUIRED_DISTRO_FEATURES = "systemd"
SRC_URI = "git://github.com/sofar/xorg-launch-helper.git;branch=master;protocol=http \
  file://0001-remove-libsystemd-daemon-dependency.patch \
  file://0002-change-vt.patch \
  "
SRC_URI[md5sum] = "8809465cd48a202895bc2a12e1923b5d"
SRC_URI[sha256sum] = "244a15f64015ce3ea17e49bdf6e1a0fb4f9af92b82fa9e05aa64cb30b5f07a4d"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=fcb02dc552a041dee27e4b85c7396067"

S = "${WORKDIR}/git"
inherit autotools pkgconfig distro_features_check
EXTRA_OECONF += " --with-systemduserunitdir=${systemd_system_unitdir}"
FILES_${PN} += "${nonarch_base_libdir}/"

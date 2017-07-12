SUMMARY = "IPTV plugin for VDR"
HOMEPAGE = "http://www.saunalahti.fi/~rahrenbe/vdr/iptv/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "http://www.saunalahti.fi/~rahrenbe/vdr/iptv/files/vdr-iptv-${PV}.tgz \
    file://0001-vdr-plugin-iptv-gcc6-build.patch \
    "
SRC_URI[md5sum] = "318d58e097895789b18a07cac46dfd1c"
SRC_URI[sha256sum] = "db9e397ed8c229f42ab71daea096c7ac66997905901639a767de193633854da2"

S = "${WORKDIR}/iptv-${PV}"

DEPENDS = "vdr curl gettext-native"

inherit pkgconfig

do_install () {
  oe_runmake install DESTDIR=${D}
}

PACKAGES_append = " ${PN}-scripts"
INSANE_SKIP_${PN} = "already-stripped"

FILES_${PN}_append = " ${libdir}/vdr/*"
FILES_${PN}-dbg_append = " ${libdir}/vdr/.debug"
FILES_${PN}-scripts = "/usr/share/vdr/plugins/iptv/"

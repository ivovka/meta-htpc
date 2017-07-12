SUMMARY = "VDR plugin to handle kodi clients."
HOMEPAGE = "https://github.com/FernetMenta/vdr-plugin-vnsiserver"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"
SRC_URI = "git://github.com/FernetMenta/vdr-plugin-vnsiserver.git;rev=${SRCREV}"
SRC_URI[md5sum] = ""
SRC_URI[sha256sum] = ""
SRCREV = "41e62a56febd0b59acf1c29f022d1d130e7af855"
S = "${WORKDIR}/git"

DEPENDS = "vdr gettext-native"
inherit pkgconfig

do_install () {
  oe_runmake install DESTDIR=${D}
}

FILES_${PN}_append = " ${libdir}/vdr/*"
FILES_${PN}-dbg_append = " ${libdir}/vdr/.debug"

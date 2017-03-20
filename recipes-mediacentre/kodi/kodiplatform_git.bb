SUMMARY = "Kodi add-on platform support library"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/util/XMLUtils.h;beginline=7;endline=20;md5=yyyy"
SRC_URI = "git://github.com/xbmc/kodi-platform.git;branch=master"
SRCREV = "36fb49371dbce49bf470a5bb1fc51b74b4a3612d"
PV = "17.1-git${SRCPV}"
FILESPATH =. "${FILE_DIRNAME}/kodiplatform-files:"

inherit cmake
S = "${WORKDIR}/git"
DEPENDS = " \
  kodi \
  libtinyxml \
  p8-platform \
  "
EXTRA_OECMAKE_append = " \
  -DCMAKE_MODULE_PATH=${STAGING_DATADIR}/kodi/cmake \
  -DBUILD_SHARED_LIBS=0 \
  "
#FILES_${PN}-dev += "${libdir}/kodiplatform/*"

do_after_install () {
  sed -i 's/-fdebug-prefix-map[^ ]*//g; s/--sysroot=[^ ]*//g; s/CXX=[^ ]*//g; s/CC=[^ ]*//g; s/DEPENDENCIES=.*/DEPENDENCIES=-lpthread -ltinyxml/g; s#-L${STAGING_DIR_TARGET}${libdir}##g' ${D}${libdir}/pkgconfig/*.pc
}
addtask after_install after do_install before do_populate_sysroot

PNBLACKLIST[xbmc] = ""

SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Helix \
           file://0001-configure-don-t-run-python-distutils-to-find-STAGING.patch \
"
SRCREV = "ad747d9f57299f70e37089924c16c382451bfd8a"
PV = "14.0+gitr${SRCPV}"
PR = "r0"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"

#CACHED_CONFIGUREVARS += " \
#    gl_cv_func_gettimeofday_clobber=no \
#    ac_cv_lib_bluetooth_hci_devid=no \
#    PYTHON_VERSION="2.7" \
#    PYTHON_CPPFLAGS="-I${STAGING_BINDIR}/usr/include/python2.7" \
#    PYTHON_LDFLAGS="-L${STAGING_BINDIR}/usr/lib/python2.7 -lpython2.7" \
#    PYTHON_SITE_PKG="${STAGING_BINDIR}/usr/lib/python2.7/site-packages" \
#    ac_python_version="2.7" \
#"

CACHED_CONFIGUREVARS += " \
    gl_cv_func_gettimeofday_clobber=no \
    ac_cv_lib_bluetooth_hci_devid=no \
"

B = "${S}"

DEPENDS = "gperf-native cmake-native zip-native curl libmodplug mpeg2dec fribidi boost libtinyxml zlib sqlite3 libpcre jasper libpng libass libvorbis tiff lzo openssl yajl libxml2 libxslt freetype taglib libdrm libxrandr virtual/libsdl ffmpeg libcdio python"
#DEPENDS = "libusb1 libcec libplist expat yajl libxmu ffmpeg samba fontconfig python libass libmicrohttpd wavpack libmms libsdl-image libsdl-mixer virtual/egl mysql5 libmms faad2 libcdio lzo enca avahi libsamplerate0 libxinerama libxrandr libxtst bzip2 virtual/libsdl libmad"

#--boost
#
#--boost
#
#--libgl
#--libglew
#--libglu
#
#--ass
#--mpeg2dec
#jpeglib
#ogg
#--vorbis
#--libmodplug
#--curl
#jpeg
#bz2
#--tiff
#pthread
#--liblzo2
#--libz
#--libcrypto
#libssl
#--yajl
#--tinyxml
#--libxml-2.0
#--libxslt
#--fribidi
#--sqlite3
#--libpng
#libpcrecpp
#--libpcre
#--freetype2
#--taglib>=1.8
#--jasper
#libx11
#xext
#libdrm
#
#
#conditional:
#alsa
#dbus
#microhttpd
#--Xrandr
#librtmp
#smbclient
#libnfs
#libafpclient
#libplist
#libshairplay
#libudev
#libusb
#libcec
#libcap
#vdpau
#libva (vaapi)
#vtbdecoder
#
#
#programms:
#--swig
#--jre
#--cmake
#--gperf
#--unzip
#--zip
#nasm

EXTRA_OECONF_append = " \
    --disable-mysql \
    --disable-sdl \
    --disable-webserver \
    --disable-libcec \
    --disable-ssh \
    --disable-samba \
    --disable-avahi \
    --disable-gtest \
    --disable-airplay \
    --disable-joystick \
    --with-ffmpeg=shared \
    "
RRECOMMENDS_${PN} = ""
RRECOMMENDS_${PN}_remove = "libcec mesa-demos"
RRECOMMENDS_${PN}_append = " python-json \
    "
RRECOMMENDS_${PN}_libc-glibc = ""
RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-utf-8 glibc-gconv-unicode glibc-gconv-utf-32"

FILES_${PN}_append = " ${datadir}/kodi ${libdir}/kodi/*"
FILES_${PN}-dbg_append = " ${libdir}/kodi/.debug ${libdir}/kodi/addons/*/.debug ${libdir}/kodi/system/.debug ${libdir}/kodi/system/players/*/.debug"

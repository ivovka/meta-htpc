include kodi.inc
FILESPATH =. "${FILE_DIRNAME}/files:"

inherit cmake gettext pythonnative
S = "${WORKDIR}/git"
OECMAKE_SOURCEPATH = "${S}/project/cmake"

# avahi - мне не нужен
# dcadec - deprecated в пользу ffmpeg
# libmicrohttpd - нужен
# libplist - нужна для airplay
# libssh - проверить, для чего нужно
# в openelec есть:
# опционально:
# libcec
# espeak
# pulseaudio
# libbluray
# avahi nss-mdns
# libplist
# libshairplay
# libnfs
# samba
# libmicrohttpd
# libssh

DEPENDS = " \
  cmake-native \
  gperf-native \
  swig-native \
  texturepacker-native \
  jsonschemabuilder-native \
  yasm-native \
  zip-native \
  alsa-lib \
  boost \
  bzip2 \
  crossguid \
  curl \
  ffmpeg \
  freetype \
  fribidi \
  giflib \
  jasper \
  jpeg \
  libass \
  libcdio \
  libdrm \
  libdvdnav \
  libmodplug \
  libpcre \
  libpng \
  libtinyxml \
  libxml2 \
  libxslt \
  lzo \
  mpeg2dec \
  openssl \
  python \
  sqlite3 \
  taglib \
  virtual/egl \
  yajl \
  zlib \
  ${@enable_glew(bb, d)} \
  "

PROVIDES = "xbmc"
PACKAGECONFIG ??= " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', ' x11', '', d)} opengl"
PACKAGECONFIG_append_ion330 = " vdpau"

PACKAGECONFIG[opengl] = "-DENABLE_OPENGL=ON -DENABLE_OPENGLES=OFF,-DENABLE_OPENGL=OFF -DENABLE_OPENGLES=ON,virtual/gl"
PACKAGECONFIG[openglesv2] = "-DENABLE_OPENGL=OFF -DENABLE_OPENGLES=ON,-DENABLE_OPENGL=ON -DENABLE_OPENGLES=OFF,virtual/egl"
PACKAGECONFIG[vaapi] = "-DENABLE_VAAPI=ON,-DENABLE_VAAPI=OFF,libva"
PACKAGECONFIG[vdpau] = "-DENABLE_VDPAU=ON,-DENABLE_VDPAU=OFF,libvdpau"
PACKAGECONFIG[mysql] = "-DENABLE_MYSQLCLIENT=ON,-DENABLE_MYSQLCIENT=OFF,mysql5"
PACKAGECONFIG[x11] = "-DENABLE_X11=ON,-DENABLE_X11=OFF,libxinerama libxmu libxrandr libxtst"
PACKAGECONFIG[pulseaudio] = "-DENABLE_PULSEAUDIO=ON,-DENABLE_PULSEAUDIO=OFF,pulseaudio"
PACKAGECONFIG[lcms] = "-DENABLE_LCMS2=ON,-DENABLE_LCMS2=OFF,lcms"

# To find Java
OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

EXTRA_OECMAKE_append = " \
  -DNATIVEPREFIX=${STAGING_DIR_NATIVE}${prefix_native} \
  -DWITH_ARCH=${TARGET_ARCH} \
  -DENABLE_SDL=OFF \
  -DENABLE_MICROHTTPD=OFF \
  -DENABLE_CEC=OFF \
  -DENABLE_SSH=OFF \
  -DENABLE_SMBCLIENT=OFF \
  -DENABLE_AVAHI=OFF \
  -DENABLE_PLIST=OFF \
  -DENABLE_INTERNAL_FFMPEG=OFF \
  -DENABLE_INTERNAL_CROSSGUID=OFF \
  -DWITH_TEXTUREPACKER=${STAGING_BINDIR_NATIVE}TexturePacker \
  -DENABLE_LDGOLD=OFF \
  -DCMAKE_SHARED_LINKER_FLAGS:STRING="${OECMAKE_CXX_LINK_FLAGS}" \
  "

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR

def enable_glew(bb, d):
    if bb.utils.contains('PACKAGECONFIG', 'x11', True, False, d) and bb.utils.contains('DISTRO_FEATURES', 'opengl', True, False, d):
        return "glew"
    return ""

INSANE_SKIP_${PN} = "already-stripped"

FILES_${PN} += "${datadir}/xsessions ${datadir}/icons ${libdir}/xbmc ${datadir}/xbmc"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

RDEPENDS_{PN} += "python-threading python-shell python-compression python-imaging"
RRECOMMENDS_${PN}_append = " \
  python \
  python-ctypes \
  python-lang \
  python-re \
  python-netclient \
  python-html \
  python-difflib \
  python-json \
  python-zlib \
  python-shell \
  python-sqlite3 \
  "
RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-utf-8 glibc-gconv-unicode glibc-gconv-utf-32"
RPROVIDES_${PN} += "xbmc"

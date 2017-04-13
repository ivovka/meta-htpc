EXTRA_FFCONF_ion330 = " \
  --cpu=atom \
  --disable-ffprobe \
  --disable-ffplay \
  --disable-ffserver \
  --disable-devices \
  "
#I do not need x11grab functionality and virtual/libsdl dependency
PACKAGECONFIG_remove_tvz = "x11"
PACKAGECONFIG_append_tvz = " bzlib gpl openssl x264 vdpau avresample"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,virtual/libvdpau"

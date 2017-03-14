EXTRA_FFCONF_ion330 = " \
  --cpu=atom \
  --disable-ffprobe \
  --disable-ffplay \
  --disable-ffserver \
  --disable-devices \
  "
PACKAGECONFIG_append_tvz = " bzlib gpl openssl x264 vdpau"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"

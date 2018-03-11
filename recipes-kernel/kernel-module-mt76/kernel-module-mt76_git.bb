SRC_URI = "git://github.com/openwrt/mt76.git;protocol=git;rev=68b0cf17efe32623efd2a46d33b0b551bb78cbbe \
file://Makefile.patch \
"

LICENSE="CLOSED"

inherit module

# Modify these as desired
PV = "1.0+git${SRCPV}"
#SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "mt76"

EXTRA_OEMAKE += "NOSTDINC_FLAGS="-I${S} -I${STAGING_KERNEL_DIR}/usr/include/mac80211-backport/uapi -I${STAGING_KERNEL_DIR}/usr/include/mac80211-backport -I${STAGING_KERNEL_DIR}/usr/include/mac80211/uapi -I${STAGING_KERNEL_DIR}/usr/include/mac80211 -include ${STAGING_KERNEL_DIR}/usr/include/mac80211-backport/backport/autoconf.h -include ${STAGING_KERNEL_DIR}/usr/include/mac80211-backport/backport/backport.h""
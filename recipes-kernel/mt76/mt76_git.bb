SRC_URI = "git://github.com/openwrt/mt76.git;protocol=git"
LICENSE="CLOSED"

inherit module

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "mt76"
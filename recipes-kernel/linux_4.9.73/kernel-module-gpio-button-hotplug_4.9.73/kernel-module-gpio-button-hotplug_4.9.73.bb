DESCRIPTION = "Openwrt uevent to hotplug converter."
SECTION = "Openwrt button hotplug."
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://Makefile file://gpio-button-hotplug.c"

inherit module

S = "${WORKDIR}"

FILES_${PN}${KERNEL_MODULE_PACKAGE_SUFFIX} += "/lib/firmware/*"

KERNEL_MODULE_AUTOLOAD += "kernel-module-gpio-button-hotplug"

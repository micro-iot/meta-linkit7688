# linux-yocto-custom.bb:
#
#   An example kernel recipe that uses the linux-yocto and oe-core
#   kernel classes to apply a subset of yocto kernel management to git
#   managed kernel repositories.
#
#   To use linux-yocto-custom in your layer, copy this recipe (optionally
#   rename it as well) and modify it appropriately for your machine. i.e.:
#
#     COMPATIBLE_MACHINE_yourmachine = "yourmachine"
#
#   You must also provide a Linux kernel configuration. The most direct
#   method is to copy your .config to files/defconfig in your layer,
#   in the same directory as the copy (and rename) of this recipe and
#   add file://defconfig to your SRC_URI.
#
#   To use the yocto kernel tooling to generate a BSP configuration
#   using modular configuration fragments, see the yocto-bsp and
#   yocto-kernel tools documentation.
#
# Warning:
#
#   Building this example without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   defconfig: When a defconfig is provided, the linux-yocto configuration
#              uses the filename as a trigger to use a 'allnoconfig' baseline
#              before merging the defconfig into the build. 
#
#              If the defconfig file was created with make_savedefconfig, 
#              not all options are specified, and should be restored with their
#              defaults, not set to 'n'. To properly expand a defconfig like
#              this, specify: KCONFIG_MODE="--alldefconfig" in the kernel
#              recipe.
#   
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v4.x only):
#            SRC_URI += "file://0001-linux-version-tweak.patch"
#   example feature addition (for kernel v4.x only):
#            SRC_URI += "file://feature.scc"
#

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

KBRANCH="v4.9.73"

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
# SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;nocheckout=1;name=machine"
SRC_URI="git://192.168.0.9/linux-stable-bare.git;protocol=git;nobranch=1;rev=b3e88217e2f95b004da89a0ff931e1dc45d3d094"

SRC_URI += "file://linkit7688.scc \
            file://linkit7688.cfg \
            file://linkit7688-standard.scc \
            file://linkit7688-user-config.cfg \
            file://linkit7688-user-features.scc \
            file://linkit7688-user-patches.scc \
            file://defconfig \
           "
SRC_URI += "file://openwrt_files/target/linux/generic/files"
SRC_URI += "file://openwrt_files/target/linux/ramips/files-4.9"

LINUX_VERSION ?= "4.9"
LINUX_VERSION_EXTENSION_append = "-custom"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# tag: v4.2 64291f7db5bd8150a74ad2036f1037e6a0428df2
# SRCREV_machine="b3e88217e2f95b004da89a0ff931e1dc45d3d094"
# SRCREV="b3e88217e2f95b004da89a0ff931e1dc45d3d094"

PV = "${LINUX_VERSION}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_linkit7688 = "linkit7688"

PREFERRED_PROVIDER_virtual/kernel="linux-yocto-linkit7688"

KERNEL_VERSION_SANITY_SKIP="1"

# KERNEL_DEFCONFIG_linkit7688="defconfig"

FILESEXTRAPATHS_prepend:="${THISDIR}/openwrt_files:"

do_patch_prepend() {
    cp -r openwrt_files/target/linux/generic/files/* ${S}
    cp -r openwrt_files/target/linux/ramips/files-4.9/* ${S}
}

do_configure_prepend() {
    rm -rf {B}/.config
}
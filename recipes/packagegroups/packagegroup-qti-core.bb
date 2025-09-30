SUMMARY = "Group to bring Core Open Source Packages"
LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-core \
    packagegroup-qti-core-recovery \
    packagegroup-qti-core-vm \
'

RDEPENDS_${PN} = " \
    mtd-utils-ubifs \
    sec-config \
"
RDEPENDS_packagegroup-qti-core-vm = " "

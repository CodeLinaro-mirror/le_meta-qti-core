SUMMARY = "Group to bring Core Open Source Packages"
LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${TUNE_PKGARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-core \
'

RDEPENDS_${PN} = " \
    mtd-utils-ubifs \
    sec-config \
"

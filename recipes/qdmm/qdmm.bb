inherit autotools pkgconfig

DESCRIPTION = "Dynamic Heap Memory Sharing userspace library"
HOMEPAGE = "https://www.qualcomm.com/"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

FILESPATH =+ "${WORKSPACE}:"

SRC_URI:seraph = "file://vendor/qcom/opensource/qdmm"
S:seraph = "${WORKDIR}/vendor/qcom/opensource/qdmm"

FILES:${PN} += "${libdir}/libdhms.so.*"
FILES:${PN}-dev += "${includedir}/dhms_usr.h ${libdir}/libdhms.so ${libdir}/pkgconfig/libdhms.pc"

PACKAGE_ARCH = "${MACHINE_ARCH}"

from setuptools import setup, find_packages

setup(
    name='omnisci_thrift',
    version='${python.version}',
    url='http://www.stlogic.co.kr',
    packages=find_packages(),
    install_requires=['thrift==0.11.0']
)

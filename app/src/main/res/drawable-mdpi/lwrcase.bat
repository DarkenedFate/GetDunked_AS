@echo off
set LC1=%~nx1
set LC1=%LC1:"=%
set LC1=%LC1:A=a%
set LC1=%LC1:B=b%
set LC1=%LC1:C=c%
set LC1=%LC1:D=d%
set LC1=%LC1:E=e%
set LC1=%LC1:F=f%
set LC1=%LC1:G=g%
set LC1=%LC1:H=h%
set LC1=%LC1:I=i%
set LC1=%LC1:J=j%
set LC1=%LC1:K=k%
set LC1=%LC1:L=l%
set LC1=%LC1:M=m%
set LC1=%LC1:N=n%
set LC1=%LC1:O=o%
set LC1=%LC1:P=p%
set LC1=%LC1:Q=q%
set LC1=%LC1:R=r%
set LC1=%LC1:S=s%
set LC1=%LC1:T=t%
set LC1=%LC1:U=u%
set LC1=%LC1:V=v%
set LC1=%LC1:W=w%
set LC1=%LC1:X=x%
set LC1=%LC1:Y=y%
set LC1=%LC1:Z=z%
set LC1=%LC1:'=%
set LC1=%LC1:(=%
set LC1=%LC1:)=%
set LC1=%LC1:-=%
set LC1=%LC1:!=%
set LC1=%LC1:,=%
ren %1 "%LC1%"
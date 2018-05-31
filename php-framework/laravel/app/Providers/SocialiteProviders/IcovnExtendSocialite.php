<?php
/**
 *
 * User: icovn
 * Date: 5/31/2018
 * Time: 6:29 PM
 */

namespace App\Providers\SocialiteProviders;

use SocialiteProviders\Manager\SocialiteWasCalled;

class IcovnExtendSocialite
{
    /**
     * Register the provider.
     *
     * @param \SocialiteProviders\Manager\SocialiteWasCalled $socialiteWasCalled
     */
    public function handle(SocialiteWasCalled $socialiteWasCalled)
    {
        $socialiteWasCalled->extendSocialite(
            'icovn', __NAMESPACE__.'\Provider'
        );
    }
}
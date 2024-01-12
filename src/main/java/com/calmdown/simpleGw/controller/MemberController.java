package com.calmdown.simpleGw.controller;

import com.calmdown.simpleGw.domain.Member;
import com.calmdown.simpleGw.domain.MobileCarrier;
import com.calmdown.simpleGw.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("v1/admin/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "members/createMemberForm";
        }

        Member member = Member.builder()
                .name(form.getName())
                .phone(form.getPhone())
                .mobileCarrier(form.getMobileCarrier())
                .socialNumber(form.getSocialNumber())
                .gender(form.getGender())
                .limitAmount(500000L)
                .build();

        memberService.join(member);
        return "redirect:/v1/admin/members";
    }

    @GetMapping()
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    /**
     * enum
     */
    @ModelAttribute("mobileCarrierList")
    public MobileCarrier[] mobileCarrierList() {
        return MobileCarrier.values();
    }

}